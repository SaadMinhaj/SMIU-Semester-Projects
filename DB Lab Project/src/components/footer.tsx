export default function Footer() {
    return (
      <footer className="bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="mt-12 border-t border-gray-200 pt-8">
            <p className="text-base text-gray-400 text-center">
              &copy; {new Date().getFullYear()} BlogPlatform. All rights reserved.
            </p>
          </div>
        </div>
      </footer>
    );
  }