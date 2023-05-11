package com.example.jusanbookingapp.presentation.utils.extensions

import android.content.Context
import android.view.View
import com.example.jusanbookingapp.presentation.utils.FacilitiesChip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChip(context: Context, label: String){

    FacilitiesChip(context).apply {

        id = View.generateViewId()

        text = label

        addView(this)

    }}