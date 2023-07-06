interface User {
    val name: String
    val dateOfBirth: String
    val email: String
    val phone: String
    val bloodGroup: String
    val gender: String
    val district: String
    val subDistrict: String
    val location: String
}

data class Teacher(
    override val name: String,
    override val dateOfBirth: String,
    override val email: String,
    override val phone: String,
    override val bloodGroup: String,
    override val gender: String,
    override val district: String,
    override val subDistrict: String,
    override val location: String,
    val nid: String,
    val department: String,
) : User

data class Student(
    override val name: String,
    override val dateOfBirth: String,
    override val email: String,
    override val phone: String,
    override val bloodGroup: String,
    override val gender: String,
    override val district: String,
    override val subDistrict: String,
    override val location: String,
    val fatherName: String,
    val motherName: String,
    val fatherPhone: String,
    val motherPhone: String,
) : User

object FakeUserData{
    private val student01 = Student(
        name = "Mr Bean",
        dateOfBirth = "13-03-2023",
        email = "abc@gmail.com",
        phone = "01780-456789",
        bloodGroup = "A+",
        gender = "Male",
        district = "Dhaka",
        subDistrict = "USA",
        location = "20.4,23.4",
        fatherName = "Mr Bean Father",
        motherName = "Mr Bean Mother",
        fatherPhone = "01780-456789",
        motherPhone = "01780-456789",
    )
    private val teacher01 = Teacher(
        name = "Mr Bean",
        dateOfBirth = "13-03-2023",
        email = "abc@gmail.com",
        phone = "01780-456789",
        bloodGroup = "A+",
        gender = "Male",
        district = "Dhaka",
        subDistrict = "USA",
        location = "20.4,23.4",
        nid = "231234566",
        department = "Physics"
    )
    val users= listOf(student01, teacher01)
}

object UserLabels {
    const val NAME = "Name"
    const val EMAIL = "Email"
    const val PHONE = "Phone"
    const val BLOOD_GROUP = "Blood Group"
    const val GENDER = "Gender"
    const val DEPARTMENT = "Department"
    const val NID = "NID"
    const val FATHER_NAME = "Father Name"
    const val MOTHER_NAME = "Mother Name"
    const val FATHER_PHONE = "Father Phone No"
    const val MOTHER_PHONE = "Mother Phone No"
    const val DISTRICT = "District"
    const val SUB_DISTRICT = "Sub District"
    const val LOCATION = "Location"
    val labelList = listOf(
        NAME,
        EMAIL,
        PHONE,
        BLOOD_GROUP,
        GENDER,
        DEPARTMENT,
        NID,
        FATHER_NAME,
        MOTHER_NAME,
        FATHER_PHONE,
        MOTHER_PHONE,
        DISTRICT,
        SUB_DISTRICT,
        LOCATION
    )

}
