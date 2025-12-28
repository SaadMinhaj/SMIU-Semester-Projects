import { cookies } from 'next/headers';

export const getSession = () => {
  const cookieStore = cookies();
  const session = cookieStore.get('session');
  return session ? JSON.parse(session.value) : null;
};

export const setSession = (user: { id: number; email: string }) => {
  const cookieStore = cookies();
  cookieStore.set('session', JSON.stringify(user), { httpOnly: true });
};

export const clearSession = () => {
  const cookieStore = cookies();
  cookieStore.delete('session');
};