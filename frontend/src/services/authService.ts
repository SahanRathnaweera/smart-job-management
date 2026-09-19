import API from './api';

export const loginUser = async (credentials: any) => {
  const response = await API.post('/auth/login', credentials);
  if (response.data.token) {
    localStorage.setItem('token', response.data.token);
    localStorage.setItem('user', JSON.stringify(response.data.user || response.data));
  }
  return response.data;
};

export const registerUser = async (userData: any) => {
  const response = await API.post('/auth/register', userData);
  return response.data;
};

export const logoutUser = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
};