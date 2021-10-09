import React from "react";

import './carousel.scss';
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from 'react-responsive-carousel';

const MyCarousel = ({area}) => {
    return(
        <React.Fragment>
            <Carousel className={`carousel__${area}`}
                      autoPlay={true}
                      infiniteLoop={true}
                      statusFormatter={(current, total) => `Current slide: ${current} / Total: ${total}`}
                      renderArrowPrev={(onClickHandler, hasPrev, label) =>
                        hasPrev && (
                            <button type="button" onClick={onClickHandler} title={label} className="carousel__control__prev">
                                -
                            </button>
                        )}
                      renderArrowNext={(onClickHandler, hasNext, label) =>
                          hasNext && (
                              <button type="button" onClick={onClickHandler} title={label} className="carousel__control__next">
                                  +
                              </button>
                          )}
                      showArrows={true}
                      showThumbs={false}>
                <div className={`carousel__${area}__testCard`}>
                    <div className={`carousel__${area}__testCard__card`}><h2>hi</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>test</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>me</h2></div>
                </div>
                <div className={`carousel__${area}__testCard`}>
                    <div className={`carousel__${area}__testCard__card`}><h2>hi</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>test</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>me</h2></div>
                </div>
                <div className={`carousel__${area}__testCard`}>
                    <div className={`carousel__${area}__testCard__card`}><h2>hi</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>test</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>me</h2></div>
                </div>
                <div className={`carousel__${area}__testCard`}>
                    <div className={`carousel__${area}__testCard__card`}><h2>hi</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>test</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>me</h2></div>
                </div>
                <div className={`carousel__${area}__testCard`}>
                    <div className={`carousel__${area}__testCard__card`}><h2>hi</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>test</h2></div>
                    <div className={`carousel__${area}__testCard__card`}><h2>me</h2></div>
                </div>
            </Carousel>
        </React.Fragment>
    );
}

export default MyCarousel;