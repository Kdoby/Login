import Login from './component/Login';
import Signup from './component/Signup';
import Test from './component/Test';

import './App.css';

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route path="/test" element={<Test />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;