import React, {useEffect, useState} from "react";

import Title from '../../components/title/title';

import './home.scss';
import MyCarousel from "../../components/carousel/carousel";
import Sidebar from "../../components/nav-elements/sidebar/sidebar";
import AuthenticationService from "../../services/AuthenticationService";

const Home = () => {
    const [offsetY,setOffsetY] = useState(0);
    const handleScrollY = () => setOffsetY(window.pageYOffset);

    useEffect(() => {
        window.addEventListener("scroll",handleScrollY);
        document.addEventListener("mousewheel",e => {
            if(e.wheelDelta >= 0){

            }else {

            }
        });
        return () => window.removeEventListener("scroll", handleScrollY);
    },[])

    const user = {
        isLoggedIn: AuthenticationService.isUserLoggedIn(),
        changedStatus: false
    }

    const showSidebar = () => {
        if(user.isLoggedIn === true){
             return <Sidebar isLoggedIn={true}/>
        }else {
            return <Sidebar isLoggedIn={false}/>
        }
    }

    return(
        <React.Fragment>
            {showSidebar()}
            <main className="landing">
                {/* ========== INSPIRATION AREA ========== */}
                    <div className="img-inspiration"/>
                    <section className="landing__inspiration">
                        <div className="box">
                            <div className="landing__inspiration__description-container" style={{transform:`translateY(${offsetY*0.2}px)`}}>
                                <aside className="landing__inspiration__description-container__content" style={{transform:`translateX(${offsetY*0.2}px)`}}>
                                    <Title firstLine="find new"
                                           secondLine="inspirations"
                                            style={{transform:`translateX(${offsetY*-0.8}px)`}}/>
                                    <div className="landing__inspiration__description-container__content__text">
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
                                    <div className="landing__inspiration__description-container__content__promo">
                                        <p>Don’t miss a any news and <strong>follow us on social-media!</strong></p>

                                        <div className="landing__inspiration__description-container__content__promo__btn">
                                            <input
                                                className="landing__inspiration__description-container__content__promo__btn__facebook"
                                                type="button"/>
                                            <input
                                                className="landing__inspiration__description-container__content__promo__btn__instagram"
                                                type="button"/>

                                            <input
                                                className="landing__inspiration__description-container__content__promo__btn__twitter"
                                                type="button"/>
                                            <input
                                                className="landing__inspiration__description-container__content__promo__btn__pinterest"
                                                type="button"/>
                                        </div>
                                    </div>
                                </aside>
                            </div>
                            <div className="landing__inspiration__card-container"
                                 style={{transform:`translateY(${offsetY*0.2}px)`}}>
                                <MyCarousel area="right"/>
                            </div>
                        </div>
                    </section>
                {/* ========== HIGHLIGHT AREA ========== */}
                <div className="img-highlight" style={{}}/>
                <section className="landing__highlight">
                    <div className="box">
                    <div className="landing__highlight__card-container"
                         style={{transform:`translateY(${offsetY*0.2}px)`}}>
                        <MyCarousel area="left"/>
                    </div>

                    <div className="landing__highlight__description-container"
                         style={{transform:`translateY(${offsetY*0.2}px)`}}>
                        <aside className="landing__highlight__description-container__content"
                               style={{transform:`translateX(${offsetY*0.05}px)`}}>
                            <Title
                                firstLine="Tastylabs"
                                secondLine="highlights"
                                style={{transform:`translateX(${offsetY*0.02}px)`}}/>
                            <div
                                className="landing__highlight__description-container__content__text"
                                style={{transform:`translateX(${offsetY*0.02}px)`}}>
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
                            </div>
                        </aside>
                    </div>
                </div>
                </section>
            </main>
        </React.Fragment>
    )
}

export default Home;