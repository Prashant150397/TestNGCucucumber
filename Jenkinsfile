pipeline {
    agent any

    stages
    {
        stage('Build') 
        {
            steps
            {
                echo 'Build App'
            }
        }
        stage('Test') 
        {
            steps
            {
                echo 'Test App'
            }
        }
        stage('Deploy') 
        {
            steps
            {
                echoo 'Build App'
            }
        }
    }
    
post {
        always 
        {
        failure 
        {
            emailext body: 'Summary', subject: 'Learning', to: 'kprashantrajput001@gmail.com'
        }
    }
}
}
