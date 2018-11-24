package com.pihrit.photos.screens.common.controllers

interface BackPressDispatcher {

    fun registerListener(listener: BackPressListener)
    fun unregisterListener(listener: BackPressListener)

}