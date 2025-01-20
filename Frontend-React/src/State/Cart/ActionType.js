import { api } from "../../config/apiConfig";
import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  GET_CART_FAILURE,
  GET_CART_REQUEST,
  GET_CART_SUCCESS,
  REMOVE_ITEM_FROM_CART_FAILURE,
  REMOVE_ITEM_FROM_CART_REQUEST,
  REMOVE_ITEM_FROM_CART_SUCCESS,
  UPDATE_ITEM_QUANTITY_FAILURE,
  UPDATE_ITEM_QUANTITY_REQUEST,
  UPDATE_ITEM_QUANTITY_SUCCESS,
} from "./Action";

// Fetch the cart items
export const getCart = () => async (dispatch) => {
  dispatch({ type: GET_CART_REQUEST });
  try {
    const { data } = await api.get("/api/cart/");
    dispatch({ type: GET_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: GET_CART_FAILURE, payload: error.message });
  }
};

// Add an item to the cart
export const addItemToCart = (reqData) => async (dispatch) => {
  dispatch({ type: ADD_ITEM_TO_CART_REQUEST });
  try {
    const { data } = await api.put("/api/cart/add", reqData.data);
    dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: ADD_ITEM_TO_CART_FAILURE, payload: error.message });
  }
};

// Remove an item from the cart
export const removeCartItem = (reqData) => async (dispatch) => {
  dispatch({ type: REMOVE_ITEM_FROM_CART_REQUEST });
  try {
    const { data } = await api.delete(`/api/cart_items/${reqData.cartItemId}`);
    dispatch({ type: REMOVE_ITEM_FROM_CART_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: REMOVE_ITEM_FROM_CART_FAILURE, payload: error.message });
  }
};

// Update the quantity of an item in the cart
export const updateCartItem = (reqData) => async (dispatch) => {
  dispatch({ type: UPDATE_ITEM_QUANTITY_REQUEST });
  try {
    const { data } = await api.put(
      `/api/cart_items/${reqData.cartItemId}`,
      reqData.data
    );
    dispatch({ type: UPDATE_ITEM_QUANTITY_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: UPDATE_ITEM_QUANTITY_FAILURE, payload: error.message });
  }
};
