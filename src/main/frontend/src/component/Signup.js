import './Signup.css';

import React, { useState } from 'react';


export default function Signup() {
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [pwConfirm, setPwConfirm] = useState('');
    const [email, setEmail] = useState('');
    const [idDuplication, setIdDuplication] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!id) return alert("아이디를 입력하세요.");
        if (!idDuplication) return alert("아이디 중복체크를 해주세요.");
        if (!pw) return alert("비밀번호를 입력하세요.");
        if (!pwConfirm) return alert("비밀번호 확인을 입력하세요.");
        if (pw !== pwConfirm) return alert("비밀번호가 일치하지 않습니다.");
        if (!email) return alert("이메일을 입력하세요.");

        console.log("회원가입 정보:", { id, pw, email });

        // 실제 회원가입 API 요청
        // fetch("/signup_do", { method: "POST", body: JSON.stringify({ id, pw, email }) })
        //     .then(res => res.json()).then(data => console.log(data));
    };

    const checkID = () => {
        if (!id) {
            alert("아이디를 입력하세요.");
            return;
        }

        fetch(`/signup_idCheck.jsp?id=${id}`)
            .then(res => res.text())
            .then(result => {
                if (result.trim() === "available") {
                    alert("사용 가능한 아이디입니다.");
                    setIdDuplication(true);
                } else {
                    alert("이미 존재하는 아이디입니다.");
                    setIdDuplication(false);
                }
            })
            .catch(err => {
                console.error("ID 중복 체크 오류:", err);
            });
    };

    return (
        <div className="login_pg">
            <div className="logbox">
                <form onSubmit={handleSubmit}>
                    <div style={{ width:"100%", margin:"50px 0" }}>
                        <label htmlFor="id">ID</label>
                        <br />

                        <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}></input>
                        <button type="button" onClick={checkID}>Duplicate Check</button>
                    </div>

                    <div style={{ width:"100%", margin:"50px 0" }}>
                        <label htmlFor="pw">PASSWORD</label>
                        <br />

                        <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}></input>
                    </div>

                    <div style={{ width:"100%", margin:"50px 0" }}>
                        <label htmlFor="pwConfirm">PASSWORD CHECK</label>
                        <br />

                        <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}></input>
                    </div>

                    <div style={{ width:"100%", margin:"50px 0" }}>
                        <label htmlFor="email">E-MAIL</label>
                        <br />

                        <input style={{ height:"40px", width:"100%", border:"2px solid black", borderRadius:"10px", margin:"20px 0"}}></input>
                    </div>

                    <div>
                        <input type="submit" value="SIGN UP" />
                    </div>
                </form>
            </div>
        </div>
    );
}
