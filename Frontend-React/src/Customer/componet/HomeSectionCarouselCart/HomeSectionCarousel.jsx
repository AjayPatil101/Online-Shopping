import React, { useState } from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import HomeSectionCart from "../HomeSectionCart/HomeSectionCart";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import { Button } from "@mui/material";
import { mens_kurta } from "../../../Data/mens_kurta";

const HomeSectionCarousel = ({data,secitionName}) => {
  const [activeIndex, setActiveIndex] = useState(0);
  const carouselRef = React.useRef(null); // Create a reference for the carousel

  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5 },
  };

  const items = data.slice(0, 10)
    .map((item) => <HomeSectionCart prodact={item} />);

  const setPrevious = () => {
    if (activeIndex > 0) {
      const newIndex = activeIndex - 1;
      setActiveIndex(newIndex);
      carouselRef.current.slideTo(newIndex); // Use AliceCarousel's slideTo method
    }
  };

  const setNext = () => {
    if (activeIndex < items.length - 1) {
      const newIndex = activeIndex + 1;
      setActiveIndex(newIndex);
      carouselRef.current.slideTo(newIndex); // Use AliceCarousel's slideTo method
    }
  };

  return (
    <div className="border">
        <h2 className="text-2x1 font-extrabold text-gray-800 py-5">{secitionName}</h2>
      <div className="relative p-5">
        <AliceCarousel
          ref={carouselRef} // Attach the reference to the carousel
          items={items}
          responsive={responsive}
          disableButtonsControls
          disableDotsControls
          infinite
          onSlideChange={({ item }) => setActiveIndex(item)}
          activeIndex={activeIndex}
        />
        {activeIndex !== 0 && (
          <Button
            variant="contained"
            className="z-50 bg-white"
            onClick={setPrevious}
            sx={{
              position: "absolute",
              top: "8rem",
              left: "0rem",
              transform: "translateX(-50%) rotate(90deg)",
              bgcolor: "white",
            }}
            aria-label="previous"
          >
            <KeyboardArrowRightIcon
              sx={{ transform: "rotate(90deg)", color: "black" }}
            />
          </Button>
        )}
       {activeIndex !== items.length - 5 && <Button
          variant="contained"
          className="z-50 bg-white"
          onClick={setNext}
          sx={{
            position: "absolute",
            top: "8rem",
            right: "0rem",
            transform: "translateX(50%) rotate(90deg)",
            bgcolor: "white",
          }}
          aria-label="next"
        >
          <KeyboardArrowRightIcon
            sx={{ transform: "rotate(-90deg)", color: "black" }}
          />
        </Button>}
      </div>
    </div>
  );
};

export default HomeSectionCarousel;
