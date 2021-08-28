package com.example.lyondrydelivery.Class;

import android.util.Patterns;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.lyondrydelivery.R;
import com.google.common.collect.Range;

public class Validations {
    private AwesomeValidation awesomeValidation;



    private void addValidationToViews() {

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

//        awesomeValidation.addValidation(this, R.id.etName, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
//
//        awesomeValidation.addValidation(this, R.id.etEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
//        awesomeValidation.addValidation(this, R.id.etConfirmEmail, R.id.etEmail, R.string.invalid_confirm_email);
//
//        String regexPassword = ".{8,}";
//        awesomeValidation.addValidation(this, R.id.etPassword, regexPassword, R.string.invalid_password);
//        awesomeValidation.addValidation(this, R.id.etConfirmPassword, R.id.etPassword, R.string.invalid_confirm_password);
//
//        awesomeValidation.addValidation(this, R.id.etPhone, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
//        awesomeValidation.addValidation(this, R.id.etAge, Range.closed(12, 60), R.string.invalid_age);
    }

}
