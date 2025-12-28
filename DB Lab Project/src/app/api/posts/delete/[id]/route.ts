import { NextResponse } from 'next/server';
import { db } from '@/lib/db';
import { getSession } from '@/lib/auth';

export async function DELETE(req: Request, { params }: { params: { id: string } }) {
  const session = getSession();

  if (!session) {
    return NextResponse.json({ message: 'Unauthorized' }, { status: 401 });
  }

  const [post] = await db.execute('SELECT * FROM posts WHERE id = ? AND user_id = ?', [params.id, session.id]);
  if ((post as any[]).length === 0) {
    return NextResponse.json({ message: 'Not found or unauthorized' }, { status: 404 });
  }

  await db.execute('DELETE FROM posts WHERE id = ?', [params.id]);
  return NextResponse.json({ message: 'Post deleted successfully' });
}