#!/bin/bash
# this is an example script. Change the parameters according to your local setup
export ANDROID_SDK=~/android-sdks
export PATH=$ANDROID_SDK/tools:$PATH
export OPENJFX_DIR=~/dalvik-sdk
export WORKDIR=~/git/camp
export PROJECT_DIR=~/git/camp/javafxclient/dist
gradle --info createProject -PDEBUG -PDIR=$WORKDIR -PPACKAGE=zuehlke.food -PNAME=androidfxclient -PANDROID_SDK=$ANDROID_SDK -PJFX_SDK=$OPENJFX_DIR -PJFX_APP=$PROJECT_DIR -PJFX_MAIN=zuehlke.food.ZenFoodApp
