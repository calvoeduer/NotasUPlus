package com.example.parcial1.data
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.parcial1.model.Activity
import com.example.parcial1.model.Qualification
import com.example.parcial1.model.Subject

private const val SUBJECTS_TABLE = "subjects"
private const val QUALIFICATIONS_TABLE = "qualifications"
private const val ACTIVITIES_TABLE = "activities"

class QualificationDatabase(private val context: Context) {
    private val dbConnection: SQLiteDatabase = context.openOrCreateDatabase(
        QUALIFICATIONS_TABLE, Context.MODE_PRIVATE, null)

    init {
        dbConnection.execSQL("CREATE TABLE IF NOT EXISTS $SUBJECTS_TABLE(code TEXT PRIMARY KEY, name TEXT, definitive DECIMAL)")
        dbConnection.execSQL("CREATE TABLE IF NOT EXISTS $QUALIFICATIONS_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT, cort INTEGER, subject_code TEXT, " +
                "FOREIGN KEY(subject_code) REFERENCES $SUBJECTS_TABLE(code))")
        dbConnection.execSQL("CREATE TABLE IF NOT EXISTS $ACTIVITIES_TABLE(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, note REAL, percent REAL, qualification_id INTEGER, " +
                "FOREIGN KEY(qualification_id) REFERENCES $QUALIFICATIONS_TABLE(id))")
    }

    fun saveSubject(subject: Subject): Boolean {
        val subjectValores = ContentValues()
        subjectValores.put("code", subject.code)
        subjectValores.put("name", subject.name)
        subjectValores.put("definitive", subject.definitive)

        return if (dbConnection.insert(SUBJECTS_TABLE, null, subjectValores) > 0) {
            subject.qualifications.forEach {
                val qualificationContentValues = ContentValues()
                qualificationContentValues.put("cort", it.cort)
                qualificationContentValues.put("subject_code", subject.code)

                dbConnection.insert(QUALIFICATIONS_TABLE, null, qualificationContentValues)
            }

            true
        } else false
    }

    fun getSubjects(): ArrayList<Subject> {
        val subjectCursor: Cursor = dbConnection.rawQuery("SELECT * FROM $SUBJECTS_TABLE", null)
        val subjects: ArrayList<Subject> = ArrayList()

        if (subjectCursor.count > 0) {
            subjectCursor.moveToFirst()
            do {
                val subject = Subject(subjectCursor.getString(0), subjectCursor.getString(1))
                subjects.add(subject)
            } while (subjectCursor.moveToNext())
        }

        return subjects
    }

    fun getSubject(code: String): Subject? {
        val subjectCursor: Cursor = dbConnection.rawQuery("SELECT * FROM $SUBJECTS_TABLE WHERE code = ?", arrayOf(code))

        if (subjectCursor.count == 0)
            return null

        val subject = Subject(subjectCursor.getString(0), subjectCursor.getString(1))
        val qualificationCursor = dbConnection.rawQuery("SELECT * FROM $QUALIFICATIONS_TABLE WHERE subject_code = ?", arrayOf(subject.code))
        if (qualificationCursor.count > 0) {
            qualificationCursor.moveToFirst()
            do {
                val qualification = Qualification()
                qualification.id = qualificationCursor.getInt(0)
                qualification.cort = qualificationCursor.getInt(1)

                qualification.activities = getQualificationActivities(qualification.id)

                subject.addQualification(qualification)
            } while (qualificationCursor.moveToNext())
        }

        return subject
    }

    private fun getQualificationActivities(qualificationId: Int): ArrayList<Activity> {
        val activitiesCursor = dbConnection.rawQuery("SELECT * FROM $ACTIVITIES_TABLE WHERE qualification_id = ?", arrayOf(qualificationId.toString()))
        val activities: ArrayList<Activity> = ArrayList()

        if (activitiesCursor.count > 0) {
            activitiesCursor.moveToFirst()
            do {
                val activity = Activity()
                activity.id = activitiesCursor.getInt(0)
                activity.name = activitiesCursor.getString(1)
                activity.note = activitiesCursor.getFloat(2)
                activity.percent = activitiesCursor.getFloat(3)

                activities.add(activity)
            } while (activitiesCursor.moveToNext())
        }

        return activities
    }

    fun saveActivity(activity: Activity, qualificationId: Int): Boolean {
        val activityContentValues = ContentValues()

        activityContentValues.put("name", activity.name)
        activityContentValues.put("note", activity.note)
        activityContentValues.put("percent", activity.percent)
        activityContentValues.put("qualification_id", qualificationId)

        return dbConnection.insert(ACTIVITIES_TABLE, null, activityContentValues) > 0
    }

    fun updateActivity(activity: Activity): Boolean {
        val activityContentValues = ContentValues()

        activityContentValues.put("name", activity.name)
        activityContentValues.put("note", activity.note)
        activityContentValues.put("percent", activity.percent)

        return dbConnection.update(ACTIVITIES_TABLE, activityContentValues, "id = ?", arrayOf(activity.id.toString())) > 0
    }

    fun deleteActivity(activityCode: Int): Boolean {
        return dbConnection.delete(ACTIVITIES_TABLE, "id = ?", arrayOf(activityCode.toString())) > 0
    }
}