import { api } from "../../confing/apiConfing";

export const findProducts = (reqData) => async (dispatch) => {
  dispatch({ type: "FIND_PRODUCTS_REQUEST" });

  const {
    color = [],
    sizes = [],
    minPrice,
    maxPrice,
    minDiscount,
    category,
    stock,
    sort,
    pageNumber ,
    pageSize = 10,
  } = reqData;
  console.log("Responce data : ", reqData);

  try {
       
    const { data } = await api.get(
      `/api/products?color=${color}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDisc=${minDiscount}&category=${category}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
    );
    console.log("api data : ", data);
    dispatch({ type: "FIND_PRODUCTS_SUCCESS", payload: data });
  } catch (error) {
    console.error("Error fetching products:", error);
    dispatch({ type: "FIND_PRODUCTS_FAILED", payload: error.message });
  }
};

export const findProductsById = (reqData) => async (dispatch) => {
  dispatch({ type: "FIND_PRODUCT_BY_ID_REQUEST" });

  const { productId } = reqData;

  try {
    const { data } = await api.get(`/api/products/id/${productId}}`);
    dispatch({ type: "FIND_PRODUCT_BY_ID_SUCCESS", payload: data });
  } catch (error) {
    console.error("Error fetching products:", error);
    dispatch({ type: "FIND_PRODUCT_BY_ID_FAILURE", payload: error.message });
  }
};
