package ui.course_enrollment.course_details

enum class CourseTitle {
    Bangla, English, Math, Physics
}

enum class CourseCode {
    Science0901, Arts0901, Other0001
}

enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class TeachingStrategy {
    Lecture, Exercise
}

enum class AssessmentStrategy {
    Assignment, Quiz, ShortQuestion
}

enum class TimeRule {
    AM, PM
}

data class MyTime(
    val hour: Int,
    val min: Int,
    val timeRule: TimeRule,
)

interface Schedule {
    val dayOfWeek: Day
    val startTime: MyTime
    val endTime: MyTime
}

data class FixedSchedule(
    override val dayOfWeek: Day,
    override val startTime: MyTime,
    override val endTime: MyTime,
) : Schedule

interface MarkDistribution {
    val finalExamMarks: Int
    val classTestMarks: Int
    val classAttendanceMarks: Int
}

data class MarkDistributionImp(
    override val finalExamMarks: Int,
    override val classTestMarks: Int,
    override val classAttendanceMarks: Int,
) : MarkDistribution


interface Syllabus {
    val courseTitle: CourseTitle
    val courseCode: CourseCode
    val credit: Float
    val contactHoursPerWeek: Float
    val totalMarks: Int
    val prerequisites: List<CourseCode>
    val markDistribution: MarkDistribution
    val objective: String
    val recommendedBooks: List<String>
}

data class UniversityStyleSyllabus(
    override val courseTitle: CourseTitle,
    override val courseCode: CourseCode,
    override val credit: Float,
    override val contactHoursPerWeek: Float,
    override val totalMarks: Int,
    override val prerequisites: List<CourseCode>,
    override val markDistribution: MarkDistribution,
    override val objective: String,
    val courseLearningOutcomes: List<String>,
    val topicDetails: List<TopicDetails>,
    override val recommendedBooks: List<String>,
) : Syllabus


interface TopicDetails {

}

data class TopicDetailsImp(
    val unitLearningOutcomes: List<String>,
    val courseContent: List<String>,
    val teachingStrategies: List<TeachingStrategy>,
    val assessmentStrategies: List<AssessmentStrategy>,
) : TopicDetails

data class Course(
    val weekSchedule: List<Schedule>,
    val syllabus: Syllabus,
    val markDistribution: MarkDistribution,
    val courseCode: CourseCode,
)

/*
Creating fake data
 */
object CourseComponentFakeData {
    val schedules = listOf(
        FixedSchedule(
            dayOfWeek = Day.SATURDAY,
            startTime = MyTime(hour = 10, min = 0, timeRule = TimeRule.AM),
            endTime = MyTime(hour = 11, min = 0, timeRule = TimeRule.AM)
        ), FixedSchedule(
            dayOfWeek = Day.MONDAY,
            startTime = MyTime(hour = 10, min = 0, timeRule = TimeRule.AM),
            endTime = MyTime(hour = 11, min = 0, timeRule = TimeRule.AM)
        )
    )

    val assessmentStrategies01 = listOf(
        AssessmentStrategy.Assignment,
        AssessmentStrategy.Quiz,
        AssessmentStrategy.ShortQuestion
    )
    val teachingStrategies01 = listOf(
        TeachingStrategy.Lecture,
        TeachingStrategy.Exercise
    )
    val markDistribution = MarkDistributionImp(
        finalExamMarks = 72,
        classTestMarks = 20,
        classAttendanceMarks = 8
    )

    val topicDetails01 = TopicDetailsImp(
        unitLearningOutcomes = listOf(
            "Define asymptotic notation",
            "Discuss different asymptotic notation",
        ),
        courseContent = listOf(
            "Introduction",
            "The omega notation",
            "The theta notation",
        ),
        teachingStrategies = teachingStrategies01,
        assessmentStrategies = assessmentStrategies01

    )

    val syllabus01 = UniversityStyleSyllabus(
        courseTitle = CourseTitle.Math,
        courseCode = CourseCode.Science0901,
        credit = 4.0f,
        contactHoursPerWeek = 2.0f,
        totalMarks = 100,
        prerequisites = listOf(
            CourseCode.Science0901,
            CourseCode.Arts0901,
            CourseCode.Other0001
        ),
        markDistribution = markDistribution,
        objective = "This the course of class 9-10",
        courseLearningOutcomes = listOf(
            "Introduction to basic physics",
            "Learn the Newtons law",
            "Learn the basic computation"
        ),
        topicDetails = listOf(topicDetails01),
        recommendedBooks = listOf("Physics 01")
    )

    val course01 = Course(
        weekSchedule = schedules,
        syllabus = syllabus01,
        markDistribution = markDistribution,
        courseCode = CourseCode.Science0901
    )
}
