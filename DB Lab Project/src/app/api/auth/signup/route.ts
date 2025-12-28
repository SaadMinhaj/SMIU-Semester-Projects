import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
// import bcrypt from 'bcryptjs'; 

export async function POST(req: Request) {
  const { email, password } = await req.json();

  const [existingUser] = await db.execute('SELECT * FROM users WHERE email = ?', [email]);
  if (existingUser.length > 0) {
    return NextResponse.json({ message: 'Email already in use' }, { status: 400 });
  }

  // const hashedPassword = await bcrypt.hash(password, 10);
  await db.execute('INSERT INTO users (email, password) VALUES (?, ?)', [email, password]);

  return NextResponse.json({ message: 'User created successfully' });
}