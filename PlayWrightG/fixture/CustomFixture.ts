//initialises all the different pages

import { HomePage } from "../pages/HomePage"
import {test as base} from '@playwright/test'

type CustomFixtures = {

    homePage: HomePage;

}

//
export const test = base.extend<CustomFixtures>({

    homePage:async ({page}, use) => {
        await use (new HomePage(page));
    }
});