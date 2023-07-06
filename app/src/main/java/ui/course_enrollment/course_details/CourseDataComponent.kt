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

interface Schedule {
    val dayOfWeek: Day
    val startTime: String
    val endTime: String
}

data class FixedSchedule(
    override val dayOfWeek: Day,
    override val startTime: String,
    override val endTime: String,
) : Schedule


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

interface TopicDetails {

}

data class TopicDetailsImp(
    val unitLearningOutcomes: List<String>,
    val courseContent: List<String>,
    val teachingStrategy: TeachingStrategy,
    val assessmentStrategy: AssessmentStrategy,
) : TopicDetails

data class Course(
    val schedule: Schedule,
    val syllabus: Syllabus,
    val markDistribution: MarkDistribution,
    val courseCode: CourseCode,
)

