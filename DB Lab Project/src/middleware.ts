import { NextResponse } from 'next/server';
import { getSession } from './lib/auth';

export function middleware(req: Request) {
  const session = getSession();
  if (!session) {
    return NextResponse.redirect(new URL('/login', req.url));
  }
  return NextResponse.next();
}

export const config = {
  matcher: ['/dashboard', '/posts/create', '/posts/edit/:id'],
};