import { Grid } from "@mui/material";
import React from "react";
import AdjustIcon from "@mui/icons-material/Adjust";
import { useNavigate } from "react-router-dom";
const OrderCart = () => {
  const navigation = useNavigate();
  return (
    <div onClick={()=>navigation(`/account/order/${5}`)} className="p-5 shadow-md shadow-black hover:shadow-2xl border">
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item sx={6}>
          <div className="flex cursor-pointer">
            <img
              className="w-[5rem] h-[5rem] object-cover object-top"
              src="https://rukminim1.flixcart.com/image/612/612/kb1470w0/jean/x/r/a/30-11274626-roadster-original-imafsgsthk6gdpjg.jpeg?q=70"
              alt=""
            />
            <div className="ml-5 space-y-2">
              <p className="">Men Regular Mid Rise Blue Jeans</p>
              <p className="opacity-50 text-xs font-semibold">Size: M</p>
              <p className="opacity-50 text-xs font-semibold">Color: Black</p>
            </div>
          </div>
        </Grid>
        <Grid item xs={2}>
          <p > ₹125</p>
        </Grid>
        <Grid item xs={4}>
          {true && (
            <div>
              <p >
                <AdjustIcon
                  sx={{ width: "15px", height: "15px" }}
                  className="text-green-600 mr-2"
                />
                <span>Delivered On March 03 </span>
              </p>
            </div>
          )}
          <p className="text-sm">Your Item Has Been Delivered</p>
          {false && (
            <p className="opacity-50 text-xs font-semibold">
              Expected Delivery On Mar 03
            </p>
          )}
        </Grid>
      </Grid>
    </div>
  );
};

export default OrderCart;
