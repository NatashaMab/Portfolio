//the place where we will place anything that we do in our application - in terms of actions (click, insert, verification)
//any common thing we will do in our application needs to be on our base page
//any reusable actions, features

import {expect, Locator, Page} from '@playwright/test'

export class BasePage{

    //constructor
    constructor(public page: Page){}

    //to call the url once
    async GoToUrl(url: string){
        await this.page.goto(url);
    }
    //in our config file we are navigating to the url already so why are we creating a function to do that again?

    async ClickElement(locator: Locator){
        await locator.click(); 
    }

    async EnterText(locator: Locator, text: string){
        await locator.fill(text);
    }

    async VerifyElementVisible(locator: Locator){
        await expect(locator).toBeVisible;
    }
}