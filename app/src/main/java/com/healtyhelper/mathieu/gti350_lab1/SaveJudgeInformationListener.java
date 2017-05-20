package com.healtyhelper.mathieu.gti350_lab1;

/**
 * Created by Mathieu on 5/20/2017.
 *
 * This interface is to handle communication between the JudgeInformationDialog and the dialog caller.
 */
public interface SaveJudgeInformationListener {

    /**
     * @param judgeId The id used to identify which judge is being edited.
     * @param name  The new name of the judge
     * @param country The new country of the judge
     */
    public void informationsWereSaved(String judgeId, String name, String country);

}
