import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
import { getSession } from '@/lib/auth';

export async function POST(req: Request) {
  const { title, content } = await req.json();
  const session = getSession();

  if (!session) {
    return NextResponse.json({ message: 'Unauthorized' }, { status: 401 });
  }

  await db.execute('INSERT INTO posts (title, content, user_id) VALUES (?, ?, ?)', [title, content, session.id]);

  return NextResponse.json({ message: 'Post created successfully' });
}