/**
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.hussain.progosteach.lithosample;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;

import java.util.Map;
import java.util.LinkedHashMap;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.RecyclerBinder;
import com.hussain.progosteach.lithosample.lithography.DataModel;
import com.hussain.progosteach.lithosample.lithography.LithographyRootComponent;
import com.hussain.progosteach.lithosample.playground.PlaygroundComponent;

/**
 * The list of Litho demos -- Add your demos below!
 */
public final class Demos {

  private static Map<String, Component<?>> demoModels;

  private Demos() {
  }

  public static void initialize(Context context) {
    final ComponentContext c = new ComponentContext(context);
    final RecyclerBinder recyclerBinder = new RecyclerBinder(
        c,
        4.0f,
        new LinearLayoutInfo(c, OrientationHelper.VERTICAL, false));
    DataModel.populateBinderWithSampleData(recyclerBinder, c);
    demoModels = new LinkedHashMap<>();
    demoModels.put(
        "Lithography",
        LithographyRootComponent.create(c)
            .recyclerBinder(recyclerBinder)
            .build());
    demoModels.put("Playground", PlaygroundComponent.create(c).build());
  }

  public static Component<?> getComponent(String name) {
    return demoModels.get(name);
  }

  public static void addAllToBinder(RecyclerBinder recyclerBinder, ComponentContext c) {
    for (String name : demoModels.keySet()) {
      ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
      componentInfoBuilder.component(
          DemoListItemComponent.create(c)
              .name(name)
              .build());
      recyclerBinder.insertItemAt(recyclerBinder.getItemCount(), componentInfoBuilder.build());
    }
  }
}
