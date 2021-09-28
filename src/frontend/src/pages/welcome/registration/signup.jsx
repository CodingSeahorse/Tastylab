import React from "react";

import './signup.scss';

import SignUpForm from '../../../components/form/signup-form/signup-form';
import Title from "../../../components/title/title";

const SignUp = () => (
    <React.Fragment>
        <main className="welcome">
            <div className="signup">
                <aside className="signup__description-container">
                    <Title firstLine="find new"
                           secondLine="inspirations"/>
                    <p className="signup__description-container__text">
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
                    </p>
                </aside>
                <SignUpForm/>
            </div>
        </main>
    </React.Fragment>
)

export default SignUp;