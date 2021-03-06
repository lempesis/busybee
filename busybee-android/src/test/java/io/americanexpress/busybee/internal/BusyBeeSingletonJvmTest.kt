/*
 * Copyright 2019 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.americanexpress.busybee.internal

import io.americanexpress.busybee.internal.EnvironmentChecks.androidJunitRunnerIsPresent
import io.americanexpress.busybee.internal.EnvironmentChecks.junit4IsPresent
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test

class BusyBeeSingletonJvmTest {

    @Test
    fun whenJunit4IsUsed_thenWeDetectItsPresence() {
        assertThat(junit4IsPresent())
            .`as`("We are running Junit4 tests, so this should be true")
            .isTrue()
    }

    @Test
    fun whenRunningJvmTestsWithoutEspresso_thenWeDontDetectIt() {
        assertThat(androidJunitRunnerIsPresent())
            .`as`("We are in /test without espresso, so this should be false")
            .isFalse()
    }

    @Test
    fun whenTestsAreRunning_thenWeUseRealBusyBee() {
        assertThat(BusyBeeSingleton.singleton())
            .`as`("We are running tests, so must use RealBusyBee")
            .isInstanceOf(RealBusyBee::class.java)
    }
}