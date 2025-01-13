import React from "react";
import "./ProdactCart.css";
import { useNavigate } from "react-router-dom";
const ProdactCart = ({item}) => {
  const navigate = useNavigate();
  return (
    
    <div onClick={()=>navigate(`/product/${5}`)} className="prodactCart w-[15rem] m-3 transition-all cursor-pointer">
      <div className="h-[20rem]">
        <img
          className="h-full w-full object-cover object-left-top p-2"
          src={item.imageUrl}
          alt=""
        />
      </div>
      <div className="textPart bg-white p-3">
        <div>
          <p className="font-bold opacity-60">{item.brand}</p>
          <p>{item.title}</p>
        </div>
        <div className="flex item-center space-x-2">
          <p className="font-semibold"> ₹{item.discountedPrice}</p>
          <p className="line-through opacity-60">₹{item.price}</p>
          <p className="text-green-600 font-semibold"> {item.discountPersent}% off</p>
        </div>
      </div>
    </div>
  );
};

export default ProdactCart;
