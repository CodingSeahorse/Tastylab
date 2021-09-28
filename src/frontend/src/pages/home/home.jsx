import React from "react";

import Title from '../../components/title/title';

import './home.scss';

const Home = () => (
    <React.Fragment>
        <main className="landing">
            {/* ========== INSPIRATION AREA ========== */}
            <div className="landing__inspiration">
                <div className="landing__inspiration__description-container">
                    <aside className="landing__inspiration__description-container__content">
                        <Title firstLine="find new"
                               secondLine="inspirations"/>
                        <p className="landing__inspiration__description-container__content__text">
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

                    <aside className="landing__inspiration__description-container__content">
                        <p>Don’t miss a any news and <strong>follow us on social-media!</strong></p>

                        <div className="landing__inspiration__description-container__content__btn">
                            <input
                                className="landing__inspiration__description-container__content__btn__facebook"
                                type="button"/>
                            <input
                                className="landing__inspiration__description-container__content__btn__instagram"
                                type="button"/>

                            <input
                                className="landing__inspiration__description-container__content__btn__twitter"
                                type="button"/>
                            <input
                                className="landing__inspiration__description-container__content__btn__pinterest"
                                type="button"/>
                        </div>
                    </aside>
                </div>

                <div className="landing__inspiration__card-container"/>{/*TODO: DELETE IT SOON !!!*/}
            </div>

            {/* ========== HIGHLIGHT AREA ========== */}
            <div className="landing__highlight">

                <div className="landing__highlight__card-container"/> {/*TODO: DELETE IT SOON !!!*/}

                <div className="landing__highlight__description-container">
                    <aside className="landing__highlight__description-container__content">
                        <Title firstLine="Tastylabs"
                               secondLine="highlights"/>
                        <p className="landing__highlight__description-container__content__text">
                           <p>
                                Tastylab is a community platform where you<br/>
                                can easily share foodwith other people.<br/>
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
                </div>
            </div>
        </main>
    </React.Fragment>
)

export default Home;