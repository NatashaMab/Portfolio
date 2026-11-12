import {test,expect} from '@playwright/test'

test('Launch Ndosi Dev Url', async({page}) => {

    await page.goto('/');

    await page.getByRole ('button', {name: 'Login'}).click();

    await page.getByRole('textbox', {name: 'Email'}).fill("mabairenatasha@gmail.com");

    await page.getByRole('textbox', {name: 'Password'}).fill("12345678@");

    await page.getByRole ('button', {name: 'Login'}).click();

    await page.waitForTimeout(5000);

    await expect(page.getByRole('heading',{name: 'Welcome back, Natasha 👋'})).toContainText('Welcomeback,Natasha👋');
    
});