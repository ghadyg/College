
import {
  BrowserRouter as Router,
  Routes,
  Route, Redirect,Navigate
} from "react-router-dom";
import LoginPage from "./Login/LoginPage";
import Signup from "./Signup/Signup";
import ManageCourses from "./Teacher/ManageCourses/ManageCourses"
import ManageGrade from "./Teacher/ManageGrade/ManageGrade"
import StudentManageCourse from "./Student/ManageCourses/StudentManageCourse"
import StudentManageGrades from "./Student/ManageGrades/StudentManageGrades"


function App() {
  return (
    
      <main>
      <Router>
        <Routes>
          <Route exact path="/" element={<LoginPage/>} />
          <Route exact path="/Signup" element={<Signup isStudent={true}/>} />
          

          <Route exact path="/TeacherSignup" element={<Signup isStudent={false}/>} />

          <Route exact path="/ManageCourses" element={<ManageCourses/>} />
          <Route exact path="/ManageGrades" element={<ManageGrade/>} />
          
          <Route exact path="/ManageStudentCourse" element={<StudentManageCourse/>} />
          <Route exact path="/ManageStudentGrades" element={<StudentManageGrades/>} />
          
        </Routes>
        </Router>
      </main>
     
  );
}

export default App;
