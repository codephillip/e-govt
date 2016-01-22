package com.codephillip.intmain.e_govt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.LicensesDialogFragment;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.CreativeCommonsAttributionNoDerivs30Unported;
import de.psdev.licensesdialog.licenses.GnuLesserGeneralPublicLicense21;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by codephillip on 1/22/16.
 */
public class LicenceActivity extends FragmentActivity {

    // ==========================================================================================================================
    // Android Lifecycle
    // ==========================================================================================================================

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final Notices notices = new Notices();
        notices.addNotice(new Notice("Test 1", "http://example.org", "Example Person", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Test 2", "http://example.org", "Example Person 2", new GnuLesserGeneralPublicLicense21()));

        new LicensesDialog.Builder(this)
                .setNotices(notices)
                .setIncludeOwnLicense(true)
                .build()
                .show();

    }

    // ==========================================================================================================================
    // Public API
    // ==========================================================================================================================

    public void onSingleClick(final View view) {
        final String name = "LicensesDialog";
        final String url = "http://psdev.de";
        final String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void onMultipleClick(final View view) {
        new LicensesDialog.Builder(this)
                .setNotices(R.raw.notices)
                .build()
                .show();
    }

    public void onMultipleIncludeOwnClick(final View view) {
        new LicensesDialog.Builder(this)
                .setNotices(R.raw.notices)
                .setIncludeOwnLicense(true)
                .build()
                .show();
    }

    public void onMultipleProgrammaticClick(final View view) {
        final Notices notices = new Notices();
        notices.addNotice(new Notice("E-govt", "http://creativecommons.org/licenses/by-nc-nd/4.0/", "Copyright 2016 Kigenyi Phillip <codephillip@gmail.com>", new CreativeCommonsAttributionNoDerivs30Unported()));
        notices.addNotice(new Notice("okhttp",  "http://github.com/square/okhttp","Copyright 2016 Square, Inc.", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("picasso", "http://github.com/square/picasso","Copyright 2013 Square, Inc.", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Paolo Rotolo", "https://github.com/PaoloRotolo/AppIntro","Copyright 2015 Paolo Rotolo", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("TextDrawable", "https://github.com/amulyakhare/TextDrawable/","Copyright (c) 2014 Amulya Khare", new MITLicense()));
        notices.addNotice(new Notice("LicensesDialog", "http://psdev.de","Copyright 2013 Philip Schiffer <admin@psdev.de>", new ApacheSoftwareLicense20()));

        new LicensesDialog.Builder(this)
                .setNotices(notices)
                .setIncludeOwnLicense(true)
                .build()
                .show();
    }

    public void onSingleFragmentClick(final View view) {
        final String name = "LicensesDialog";
        final String url = "http://psdev.de";
        final String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);

        final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
                .setNotice(notice)
                .setIncludeOwnLicense(false)
                .build();

        fragment.show(getSupportFragmentManager(), null);
    }

    public void onMultipleFragmentClick(final View view) throws Exception {
        final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
                .setNotices(R.raw.notices)
                .build();

        fragment.show(getSupportFragmentManager(), null);
    }

    public void onMultipleIncludeOwnFragmentClick(final View view) throws Exception {
        final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
                .setNotices(R.raw.notices)
                .setShowFullLicenseText(false)
                .setIncludeOwnLicense(true)
                .build();

        fragment.show(getSupportFragmentManager(), null);
    }

    public void onMultipleProgrammaticFragmentClick(final View view) {
        final Notices notices = new Notices();
        notices.addNotice(new Notice("Test 1", "http://example.org", "Example Person", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Test 2", "http://example.org", "Example Person 2", new GnuLesserGeneralPublicLicense21()));

        final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
                .setNotices(notices)
                .setShowFullLicenseText(false)
                .setIncludeOwnLicense(true)
                .build();

        fragment.show(getSupportFragmentManager(), null);
    }

//    public void onCustomThemeClick(final View view) {
//        new LicensesDialog.Builder(this)
//                .setNotices(R.raw.notices)
//                .setIncludeOwnLicense(true)
//                .setThemeResourceId(R.style.custom_theme)
//                .setDividerColorId(R.color.custom_divider_color)
//                .build()
//                .show();
//    }
//
//    public void onCustomThemeFragmentClick(final View view) throws Exception {
//        final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
//                .setNotices(R.raw.notices)
//                .setShowFullLicenseText(false)
//                .setIncludeOwnLicense(true)
//                .setThemeResourceId(R.style.custom_theme)
//                .setDividerColorRes(R.color.custom_divider_color)
//                .build();
//
//        fragment.show(getSupportFragmentManager(), null);
//    }
}