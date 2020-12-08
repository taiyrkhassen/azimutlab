package com.example.azimutlab.widgets_taiyr

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.azimutlab.R

class CardCustomSber : LinearLayout {

    var cardTitleText: String?
        get() = cardTitle.text.toString()
        set(value) {
            if (value == null) {
                cardTitle.visibility = View.GONE
            } else {
                cardTitle.text = value
            }

        }

    var hintTitleText: String?
        get() = hintTitle.text.toString()
        set(value) {
            if (value == null) {
                hintTitle.visibility = View.GONE
            } else {
                if (cardTitleText == null && moneyText == null) {
                    hintTitle.visibility = View.VISIBLE
                    hintTitle.text = value
                    // razmer uvelichit
                } else {
                    hintTitle.visibility = View.VISIBLE
                    hintTitle.text = value
                }
            }
        }
    var nameAdditionalText: String?
        get() = nameAdditional.text.toString()
        set(value) {
            if (value == null) {
                nameAdditional.visibility = View.GONE
            } else {
                nameAdditional.text = value
            }
        }

    var moneyText: String?
        get() = moneySum.text.toString()
        set(value) {
            if (value == null) {
                moneySum.visibility = View.GONE
            } else {
                moneySum.text = value
            }

        }

    var errorTextMsg: String?
        get() = errorText.text.toString()
        set(value) {
            if (value == null) {
                errorText.visibility = View.GONE
            } else {
                errorText.text = value
            }
        }

//    var hasHintTitle: Boolean
//        get() = hintTitle.visibility == View.VISIBLE
//        set(value) {
//            if (value) {
//                hintTitle.visibility = View.VISIBLE
//            } else {
//                hintTitle.visibility = View.GONE
//            }
//        }
//
//    var hasDivider: Boolean
//        get() = divider.visibility == View.VISIBLE
//        set(value) {
//            if (value) {
//                divider.visibility = View.VISIBLE
//            } else {
//                divider.visibility = View.GONE
//            }
//        }

//    var hasError: Boolean
//        get() = errorText.visibility == View.VISIBLE
//        set(value) {
//            if (value) {
//                errorText.visibility = View.VISIBLE
//            } else {
//                errorText.visibility = View.GONE
//            }
//        }


    lateinit var hintTitle: TextView
    lateinit var errorText: TextView
    lateinit var cardTitle: TextView
    lateinit var cardHintInfo: TextView
    lateinit var moneySum: TextView
    lateinit var divider: View
    lateinit var mainContainer: ConstraintLayout
    lateinit var cardImage: ImageView
    lateinit var nameAdditional: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)

    }

    private fun init(context: Context, attrs: AttributeSet) {
        init(context)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardCustomSber)
        cardTitle.text = typedArray.getText(R.styleable.CardCustomSber_titleCard)
        hintTitle.apply {
            if (typedArray.getBoolean(R.styleable.CardCustomSber_hasHintTitle, false)) {
                visibility = View.VISIBLE
                text = typedArray.getText(R.styleable.CardCustomSber_hintTitle)
            } else {
                visibility = View.GONE
            }
        }
        errorText.apply {
            if (typedArray.getBoolean(R.styleable.CardCustomSber_hasError, false)) {
                visibility = View.VISIBLE
                text = typedArray.getText(R.styleable.CardCustomSber_errorTitle)
                setTextColor(
                    typedArray.getColor(
                        R.styleable.CardCustomSber_errorColor,
                        ResourcesCompat.getColor(resources, R.color.colorError, null)
                    )
                )
            }
        }
        divider.apply {
            visibility = if (typedArray.getBoolean(R.styleable.CardCustomSber_hasDivider, false))
                View.VISIBLE else View.GONE
        }
        cardImage.apply {
            setImageResource(
                typedArray.getResourceId(
                    R.styleable.CardCustomSber_cardImage,
                    R.id.cardIcon
                )
            )
        }
        moneySum.apply {
            text = typedArray.getText(R.styleable.CardCustomSber_moneySum)
        }
        nameAdditional.text = typedArray.getText(R.styleable.CardCustomSber_additionalInfo)

        typedArray.recycle()
    }

    private fun init(context: Context) {
        inflate(context, R.layout.card_custom_widget, this)
        hintTitle = findViewById(R.id.hintTitle)
        errorText = findViewById(R.id.errorText)
        cardTitle = findViewById(R.id.title_card)
        cardHintInfo = findViewById(R.id.hintTitle)
        moneySum = findViewById(R.id.moneySum)
        mainContainer = findViewById(R.id.mainContent)
        cardImage = findViewById(R.id.cardIcon)
        nameAdditional = findViewById(R.id.nameAdditional)
        divider = findViewById(R.id.divider)
        mainContainer.setOnClickListener {

        }

    }

    fun setCardImage(resId: Int = R.drawable.ic_card_2) {
        cardImage.visibility = View.VISIBLE
        cardImage.setImageResource(resId)
    }

    fun setClick(function: () -> Unit) {
        mainContainer.setOnClickListener {
            function()
        }
    }

}