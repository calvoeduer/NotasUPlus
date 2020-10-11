package com.example.parcial1.model

class Subject(val code: String, val name: String) {
    val qualifications : ArrayList<Qualification> = ArrayList(3)

    init {
        qualifications.addAll(arrayListOf(Qualification(), Qualification(), Qualification()))
        var cort = 1
        qualifications.forEach { it.cort = cort++ }
    }

    val definitive : Float
        get() {
            if (qualifications.size == 0)
                return 0F
            return qualifications.map { q -> q.total }.reduce { acc, fl -> acc + fl }
        }

    fun addQualification(qualification: Qualification) : Boolean {
        if (qualification.cort > 3 || qualification.cort <= 0)
            return false

        qualifications[qualification.cort - 1] = qualification //
        return true

    }


}