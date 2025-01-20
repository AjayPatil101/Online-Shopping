import Axios from "axios";
import { API_BASE_URL } from "../../confing/apiConfing";
import { GET_USER_FAILURE, GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionType";

const registerRequest = () => ({ type: REGISTER_REQUEST });
const registerSuccess = (user) => ({ type: REGISTER_SUCCESS, payload: user });
const registerFailure = (error) => ({ type: REGISTER_FAILURE, payload: error });


export const register = (userData) => async (dispatch) => {
  dispatch(registerRequest());
  try {
    const response = await Axios.post(`${API_BASE_URL}/auth/signup`, userData);
    const user = response.data;
    if (user.jwt) localStorage.setItem("jwt", user.jwt);
    dispatch(registerSuccess(user.jwt));
    console.log("user : ",user);
    return user;
  } catch (error) {
    dispatch(registerFailure(error.message));
  }
};

const loginRequest = () => ({ type: LOGIN_REQUEST });
const loginSuccess = (user) => ({ type: LOGIN_SUCCESS, payload: user });
const loginFailure = (error) => ({ type: LOGIN_FAILURE, payload: error });
export const login = (userData) => async (dispatch) => {
    dispatch(loginRequest());
    try {
      const response = await Axios.post(`${API_BASE_URL}/auth/signin`, userData);
      const user = response.data;
      if (user.jwt) localStorage.setItem("jwt", user.jwt);
      dispatch(loginSuccess(user.jwt));
      console.log("user : ",user);
      
      return user;
    } catch (error) {
      dispatch(loginFailure(error.message));
    }
  };
const getRequest = () => ({ type: GET_USER_REQUEST });
const getSuccess = (user) => ({ type: GET_USER_SUCCESS, payload: user });
const getFailure = (error) => ({ type: GET_USER_FAILURE, payload: error });
export const getUser = (jwt) => async (dispatch) => {
    dispatch(getRequest());
    try {
      const response = await Axios.get(`${API_BASE_URL}/api/users/profile` ,{
        headers: { "Authorization": `Bearer ${jwt}` },    
      });
      const user = response.data;
      dispatch(getSuccess(user));
      console.log("user : ",user);
      return user;
    } catch (error) {
      dispatch(getFailure(error.message));
    }
  };
export const logout=()=>(dispatch)=>{
    dispatch({type: LOGOUT,payload:null});
    localStorage.clear();
}