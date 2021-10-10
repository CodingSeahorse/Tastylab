import React from "react";
import './login.scss';

import '../welcome.scss';

import Title from "../../../components/title/title";

import '../../../helpers/_size-solver.scss';

import LoginForm from "../../../components/form/login-form/login-form";

const Login= () => (
    <React.Fragment>
        <main>
            <div className="welcome-img"/>
            <div className="content-container">
                <section className="login">
                    <LoginForm/>
                    <aside className="login__description-container">
                        <Title firstLine="find new"
                               secondLine="inspirations"/>
                        <div className="login__description-container__text">
                            <p>
                                Tastylab is a community platform where you<br/>
                                can easily share food with other people.<br/>
                                You will bring your cooking skills to a next level.<br/>
                                Lorem ipsum dolor sit amet, consetetur sadipscing<br/>
                                elitr, sed diam yam erat, sed diam voluptua.<br/>
                            </p>
                            <p>
                                At vero eos et accusam et justo<br/>
                                duo dolores et ea rebum. Stet clita kasd gubergren,<br/>
                                no sea takimata sanctus est Lorem ipsum dolor<br/>
                                sit amet.
                            </p>
                            <p>
                                duo dolores et ea rebum. Stet clita kasd gubergren,<br/>
                                no sea takimata sanctus est Lorem ipsum dolor sit<br/>
                                amet.
                            </p>
                        </div>
                    </aside>
                </section>
            </div>
        </main>
    </React.Fragment>
)

export default Login;