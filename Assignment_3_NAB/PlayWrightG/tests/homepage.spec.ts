import {test} from '../fixture/CustomFixture';

test.describe('Home Page Tests', () =>{
    
    test ('Navigate to Login Page', async ({homePage}) =>{
        await homePage.openNdosiPage();
        await homePage.navigateToLoginPage();
    })
})