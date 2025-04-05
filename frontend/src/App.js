import {BrowserRouter as Router,Route,Routes} from 'react-router-dom';
import './App.css';
import Register from './Pages/Register';
import Login from './Pages/Login';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={< Register/>}/>
          <Route path='login' element={<Login/>}/>  
        </Routes>

      </Router>
    
    </>
  );
}

export default App;
