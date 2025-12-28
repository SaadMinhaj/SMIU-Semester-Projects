import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
// import bcrypt from 'bcryptjs';
import { setSession } from "@/lib/auth";

export async function POST(req: Request) {
  const { email, password } = await req.json();

  const [user] = await db.execute('SELECT * FROM users WHERE email = ?', [email]);
  if (user.length === 0) {
    return NextResponse.json({ message: 'Invalid credentials' }, { status: 400 });
  }

  // const isMatch = await bcrypt.compare(password, user[0].password);
  if (password!==user[0].password) {
    return NextResponse.json({ message: 'Invalid credentials' }, { status: 400 });
  }

  setSession({ id: user[0].id, email: user[0].email });

  return NextResponse.json({ message: 'Login successful' });
}