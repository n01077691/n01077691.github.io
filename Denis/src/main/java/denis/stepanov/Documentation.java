package denis.stepanov;

/**
 * Created by Denis Stepanov on 11/5/2016.
 * This class contains information about activities and function of the app
 *
 * TODO: #1 AddTestActivity has no DB function (it has error checking for all editTexts).
 * TODO: #2 SearchActivity needs filter b departments which will display all patients stored in specific dept.
 * TODO: #3 AddTestActivity has no retrieve function for spinner (no information was found on the internet).
 * TODO: #4 Edit button in ViewInformationActivity has no implementation (see TODO #2).
 *
 * Functions:
 *  SearchbyId is working properly
 *  I used sharedPreferences for storing ID. Incrementing by 1 everytime nurse/doctor creates new patient.
 *  6-digit ID as editText would be a good idea if in search it would give ListView of all ids. Otherwise user would have to remember
 *  6 digit ID everytime they go to search.
 *
 *  AddTest has all regexp checking and responsive layout (no need for layout-land) since Relative layout works perfectly in portrait and landscape
 *
 *  Activities do not restart on changing orientation
 *
 *  Search has custom fonts (fonts were not specified of usage in docx. file).
 *
 *  Toolbar (actionbar) has custom color for text and background.
 *
 *  ViewInformation has listView that stores all patients with buttons edit(not implemented) and delete(implemented) which was not described as
 *  functioning in the document.
 *
 *  Some of the mockup in addTest do not match the agenda in the document
 *
 *  Different resolutions, picture densities and no hard coding.
 *
 *  Every class has documentation also.
 *
 *  Second Tab (fragment2) was not implemented since it has no use (since there is no login, the tabs are basically same).
 *
 */
