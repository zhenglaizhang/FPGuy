echo "Setting up Scala development environment on Mac OS X"

function setup-homebrew 
{
	ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"		
}

function brew-install 
{
	brew update
	brew install scala
	brew install git	
}

# I perfer to install latest eclipse then use update site to install Scala plugin
brew cask install eclipse-java