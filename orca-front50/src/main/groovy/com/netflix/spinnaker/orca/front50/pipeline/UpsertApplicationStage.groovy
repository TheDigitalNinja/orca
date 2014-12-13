/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.front50.pipeline

import com.netflix.spinnaker.orca.front50.tasks.UpsertApplicationTask
import com.netflix.spinnaker.orca.front50.tasks.WaitForMultiAccountPropagationTask
import com.netflix.spinnaker.orca.pipeline.LinearStage
import com.netflix.spinnaker.orca.pipeline.model.Stage
import org.springframework.batch.core.Step
import org.springframework.stereotype.Component

@Component
class UpsertApplicationStage extends LinearStage {
  public static final String MAYO_CONFIG_TYPE = "upsertApplication"

  UpsertApplicationStage() {
    super(MAYO_CONFIG_TYPE)
  }

  @Override
  protected List<Step> buildSteps(Stage stage) {
    def step1 = buildStep("upsertApplicationTask", UpsertApplicationTask)
    def step2 = buildStep("waitForMultiAccountPropagation", WaitForMultiAccountPropagationTask)
    [step1, step2]
  }
}