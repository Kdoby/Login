import './Login.css';

import React, { useState } from 'react';
import { Link } from 'react-router-dom';

export default function Login() {
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!id || !pw) {
            alert("ID와 PASSWORD를 모두 입력하세요.");
            return;
        }
        console.log("로그인 시도:", id, pw);
        // 로그인 처리 로직 추가 필요 (예: axios POST 요청)
    };

    return (

        <div>
            <div className="login_pg">
                <div className="logbox">
                    <form onSubmit={handleSubmit}>
                        <div style={{ width:"100%", margin:"50px 0" }}>
                            <label htmlFor="id">ID</label>
                            <br />

                            <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}
                                   type="text"
                                   id="id"
                                   name="id"
                                   value={id}
                                   onChange={(e) => setId(e.target.value)}
                            ></input>
                        </div>

                        <div style={{ width:"100%", margin:"50px 0" }}>
                            <label htmlFor="pw">PASSWORD</label>
                            <br />

                            <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}
                                   type="password"
                                   id="pw"
                                   name="pw"
                                   value={pw}
                                   onChange={(e) => setPw(e.target.value)}
                            ></input>
                        </div>

                        <div>
                            <input type="submit" value="LOG-IN" />
                            <div style={{ margin:"10px"}}>
                                <a href="/signup"><Link to="/signup">SIGN-UP</Link></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}
