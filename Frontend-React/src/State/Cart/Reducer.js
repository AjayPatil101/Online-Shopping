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

const initialState = {
  cart: null,
  loading: false,
  error: null,
  cartItems: [],
};
export const cartReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_ITEM_TO_CART_REQUEST:
      return { ...state, loading: true, error: null };
    case ADD_ITEM_TO_CART_SUCCESS:
      return {
        ...state,
        cartItems: [...state.cartItems, action.payload.cartItems],
        loading: true,
      };
    case ADD_ITEM_TO_CART_FAILURE:
      return { ...state, loading: false, error: action.payload };
    case GET_CART_REQUEST:
      return { ...state, loading: true,error:null };
    case GET_CART_SUCCESS:
      return {
        ...state,
        cart: action.payload,
        cartItems: action.payload.cartItems,
        loading: false,
      };
    case GET_CART_FAILURE:
      return { ...state, loading: false, error: action.payload };
    case REMOVE_ITEM_FROM_CART_REQUEST:
    case UPDATE_ITEM_QUANTITY_REQUEST:
      return { ...state, loading: true,error:null };
    case REMOVE_ITEM_FROM_CART_SUCCESS:
      return {
        ...state,
        cartItems: state.cart.cartItems.filter(
          (item) => item.id !== action.payload
        ),
        loading: false,
      };
    case UPDATE_ITEM_QUANTITY_SUCCESS:
      return {
        ...state,
        cartItems: state.cart.cartItems.map((item) =>
          item._id === action.payload.id ? action.payload : item
        ),
        loading: false,
      }; 
    case REMOVE_ITEM_FROM_CART_FAILURE:
    case UPDATE_ITEM_QUANTITY_FAILURE:
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
};
