import React from 'react'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../Pages/HomePage/HomePage'
import Cart from '../componet/Cart/Cart'
import NavigationBar from '../componet/Navigation/NavigationBar'
import Footer from '../componet/Footer/Footer'
import Prodact from '../componet/Prodact/Prodact/Prodact'
import ProdactDetails from '../componet/ProdactDetails/ProdactDetails'
import Checkout from '../componet/Checkout/Checkout'
import Order from '../componet/Order/Order'
import OrderDetails from '../componet/Order/OrderDetails'

const CustomerRoutes = () => {
  return (
    <div>
        <div>
        <NavigationBar /> 
        </div>
        <Routes >
            <Route path='/'element={<HomePage />} ></Route> 
            <Route path='/cart'element={<Cart />} ></Route> 
            <Route path='/:levelOne/:levelTwo/:levelThree'element={<Prodact />} ></Route> 
            <Route path='/product/:prodactId'element={<ProdactDetails />} ></Route> 
            <Route path='/checkout'element={<Checkout />} ></Route> 
            <Route path='/account/order'element={<Order />} ></Route> 
            <Route path='/account/order/:orderId'element={<OrderDetails />} ></Route> 

       
        {/* <Order /> */}
        {/* <OrderDetails /> */}
        </Routes>
        <div>
        <Footer />
        </div>
    </div>
  )
}

export default CustomerRoutes