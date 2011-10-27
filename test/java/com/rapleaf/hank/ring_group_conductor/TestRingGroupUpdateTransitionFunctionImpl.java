/**
 *  Copyright 2011 Rapleaf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rapleaf.hank.ring_group_conductor;

import com.rapleaf.hank.coordinator.*;
import com.rapleaf.hank.coordinator.mock.MockDomain;
import com.rapleaf.hank.coordinator.mock.MockDomainGroup;
import junit.framework.TestCase;

import java.util.*;

public class TestRingGroupUpdateTransitionFunctionImpl extends TestCase {
  private class MockRingLocal extends MockRing {
    public boolean updateCompleteCalled;
    private final DomainGroupVersion version;
    private final DomainGroupVersion updatingToVersion;

    public MockRingLocal(int number,
                         RingState state,
                         DomainGroupVersion version,
                         DomainGroupVersion updatingToVersion,
                         PartitionServerAddress... hosts) {
      super(new LinkedHashSet<PartitionServerAddress>(Arrays.asList(hosts)), null, number, state);
      this.version = version;
      this.updatingToVersion = updatingToVersion;
    }

    @Override
    public Integer getVersionNumber() {
      if (version != null) {
        return version.getVersionNumber();
      } else {
        return null;
      }
    }

    @Override
    public Integer getUpdatingToVersionNumber() {
      if (updatingToVersion != null) {
        return updatingToVersion.getVersionNumber();
      } else {
        return null;
      }
    }

    @Override
    public void updateComplete() {
      this.updateCompleteCalled = true;
    }

    @Override
    public DomainGroupVersion getVersion() {
      return version;
    }

    @Override
    public DomainGroupVersion getUpdatingToVersion() {
      return updatingToVersion;
    }
  }

  private class MockRingGroupLocal extends MockRingGroup {
    private final int currentVersion;
    private final int updatingToVersion;

    public MockRingGroupLocal(int currentVersion, int updatingToVersion, Ring... rings) {
      super(null, "myRingGroup", new LinkedHashSet<Ring>(Arrays.asList(rings)));
      this.currentVersion = currentVersion;
      this.updatingToVersion = updatingToVersion;
    }

    @Override
    public Integer getCurrentVersion() {
      return currentVersion;
    }

    @Override
    public Integer getUpdatingToVersion() {
      return updatingToVersion;
    }
  }

  private static Domain domain1 = new MockDomain("domain1");

  private static PartitionServerAddress address1 = new PartitionServerAddress("localhost", 1);
  private static PartitionServerAddress address2 = new PartitionServerAddress("localhost", 2);

  private static DomainGroup domainGroup = new MockDomainGroup("myDomainGroup");

  private static DomainGroupVersion v1 =
      new MockDomainGroupVersion(Collections.<DomainGroupVersionDomainVersion>emptySet(), domainGroup, 1);
  private static DomainGroupVersion v2 =
      new MockDomainGroupVersion(Collections.<DomainGroupVersionDomainVersion>emptySet(), domainGroup, 2);
  private static Set<DomainGroupVersionDomainVersion> domainVersions = new HashSet<DomainGroupVersionDomainVersion>() {{
    add(new MockDomainGroupVersionDomainVersion(domain1, 0));
  }};
  private static DomainGroupVersion v3 = new MockDomainGroupVersion(domainVersions, domainGroup, 2);

  public void testDownsFirstAvailableRing() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.UP, v1, v2, address1);
    MockRingLocal r2 = new MockRingLocal(2, RingState.UP, v1, v2, address2);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1, r2);
    getFunc().manageTransitions(rg);

    assertTrue("r1 should have been taken down", r1.isAllCommanded(HostCommand.GO_TO_IDLE));
    assertEquals(RingState.GOING_DOWN, r1.getState());
    assertFalse("r2 should not have been taken down", r2.isAllCommanded(HostCommand.GO_TO_IDLE));
    assertEquals(RingState.UP, r2.getState());
  }

  private RingGroupUpdateTransitionFunction getFunc() {
    return new RingGroupUpdateTransitionFunctionImpl();
  }

  public void testDownsOnlyNotYetUpdatedRing() throws Exception {
    // this ring is fully updated
    MockRingLocal r1 = new MockRingLocal(1, RingState.UP, v2, null, address1);
    MockRingLocal r2 = new MockRingLocal(2, RingState.UP, v1, v2, address2);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1, r2);
    getFunc().manageTransitions(rg);

    assertFalse("r1 should not have been taken down", r1.isAllCommanded(HostCommand.GO_TO_IDLE));
    assertEquals(RingState.UP, r1.getState());
    assertTrue("r2 should have been taken down", r2.isAllCommanded(HostCommand.GO_TO_IDLE));
    assertEquals(RingState.GOING_DOWN, r2.getState());
  }

  public void testDownsNothingIfSomethingIsAlreadyDown() throws Exception {
    for (RingState s : EnumSet.of(RingState.GOING_DOWN, RingState.COMING_UP, RingState.UPDATING, RingState.DOWN)) {
      MockRingLocal r1 = new MockRingLocal(1, s, v1, v2, address1);
      MockRingLocal r2 = new MockRingLocal(2, RingState.UP, v1, v2, address2);
      MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1, r2);
      getFunc().manageTransitions(rg);

      assertEquals("r2 should still be up", RingState.UP, r2.getState());
    }
  }

  public void testDownToUpdating() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.DOWN, v1, v2, address1);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    getFunc().manageTransitions(rg);

    assertTrue("r1 should have been set to updating", r1.isAllCommanded(HostCommand.EXECUTE_UPDATE));
    assertEquals(RingState.UPDATING, r1.getState());
  }

  public void testUpdatingToComingUp() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.UPDATING, v1, v2, address1);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    getFunc().manageTransitions(rg);

    assertTrue("r1 should have been set to starting", r1.isAllCommanded(HostCommand.SERVE_DATA));
    assertEquals(RingState.COMING_UP, r1.getState());
  }

  public void testDoesntLeaveUpdatingWhenThereAreStillHostsUpdating() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.UPDATING, v1, v2, address1);
    r1.getHostByAddress(address1).setState(HostState.UPDATING);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    getFunc().manageTransitions(rg);

    assertFalse("r1 should not have been set to starting", r1.isAllCommanded(HostCommand.SERVE_DATA));
    assertEquals(RingState.UPDATING, r1.getState());
  }

  // this case will only occur when the Ring Group Conductor has died or something.
  public void testUpdatedToComingUp() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.UPDATED, v1, v2, address1);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    getFunc().manageTransitions(rg);

    assertTrue("r1 should have been set to update complete", r1.updateCompleteCalled);
    assertTrue("r1's hosts should be commanded to start", r1.isAllCommanded(HostCommand.SERVE_DATA));
    assertEquals(RingState.COMING_UP, r1.getState());
  }

  public void testFailedUpdateNotComingUp() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.UPDATING, v1, v3, address1);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    MockHost host = ((MockHost) r1.getHostByAddress(address1));
    host.addMockDomain(domain1, 0, 1, 1);
    getFunc().manageTransitions(rg);

    assertFalse("r1 should not have been set to update complete", r1.updateCompleteCalled);
    assertFalse("r1's hosts should not be commanded to start", r1.isAllCommanded(HostCommand.SERVE_DATA));
    assertEquals(RingState.UPDATING, r1.getState());
    assertEquals("Not up to date host should have been commanded to update",
        HostCommand.EXECUTE_UPDATE, host.getLastEnqueuedCommand());
  }

  public void testComingUpToUp() throws Exception {
    MockRingLocal r1 = new MockRingLocal(1, RingState.COMING_UP, v1, v2, address1);
    r1.getHostByAddress(address1).setState(HostState.SERVING);
    MockRingGroupLocal rg = new MockRingGroupLocal(1, 2, r1);
    getFunc().manageTransitions(rg);
    assertEquals(RingState.UP, r1.getState());
  }
}
