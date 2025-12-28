import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
import { getSession } from '@/lib/auth';

export async function GET() {
//   const session = getSession();

//   if (!session) {
//     return NextResponse.json({ message: 'Unauthorized' }, { status: 401 });
//   }

  const [posts] = await db.execute('SELECT * FROM posts');
  return NextResponse.json(posts);
}