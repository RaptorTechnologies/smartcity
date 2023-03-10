import {css, html, LitElement, PropertyValues, TemplateResult, unsafeCSS} from "lit";
import {customElement} from "lit/decorators.js";
import {
    DefaultBoxShadowBottom,
    DefaultColor1,
    DefaultColor2,
    DefaultColor3,
    DefaultColor4,
    DefaultColor5,
    DefaultHeaderHeight,
} from "@openremote/core";
import {OrHeader} from "@openremote/or-app";

@customElement("custom-header")
export class CustomHeader extends OrHeader {

    // language=CSS
    static get styles() {
        return css`

            :host {
                --internal-or-header-image: var(--or-header-image, white);
                --internal-or-header-color: var(--or-header-color, var(--or-app-color1, ${unsafeCSS(DefaultColor1)}));
                --internal-or-header-icon-color: var(--or-header-icon-color, var(--or-header-text-color, var(--or-app-color3, inherit)));
                --internal-or-header-selected-color: var(--or-header-selected-color, var(--or-app-color4, ${unsafeCSS(DefaultColor4)}));
                --internal-or-header-text-color: var(--or-header-text-color, var(--or-app-color3, inherit));
                --internal-or-header-height: var(--or-header-height, ${unsafeCSS(DefaultHeaderHeight)});
                --internal-or-header-logo-margin: var(--or-header-logo-margin, 0 40px 0 0);
                --internal-or-header-logo-height: var(--or-header-logo-height, var(--internal-or-header-height, ${unsafeCSS(DefaultHeaderHeight)}));
                --internal-or-header-item-size: var(--or-header-item-size, calc(${unsafeCSS(DefaultHeaderHeight)} - 20px));
                --internal-or-header-drawer-color: var(--or-header-drawer-color, var(--or-app-color2, ${unsafeCSS(DefaultColor2)}));
                --internal-or-header-drawer-text-color: var(--or-header-drawer-text-color, var(--or-app-color3, ${unsafeCSS(DefaultColor3)}));
                --internal-or-header-drawer-item-size: var(--or-header-drawer-item-size, 30px);
                --internal-or-header-drawer-separator-color: var(--or-header-drawer-separator-color, var(--or-app-color5, ${unsafeCSS(DefaultColor5)}));

                display: block;
                z-index: 4;
            }

            #toolbar-top {
                display: flex;
                padding: 0;
            }

            #logo-mobile {
                margin: 8px;
                height: calc(var(--internal-or-header-logo-height) - 16px);
                display: block;
            }

            #logo {
                display: none;
            }

            #header {
                opacity: 1;
                width: 100%;
                height: var(--internal-or-header-height);
                text-align: center;
                background-color: var(--internal-or-header-color);
                background-image: var(--internal-or-header-image);
                color: var(--internal-or-header-text-color);
                --or-icon-fill: var(--internal-or-header-text-color);
                --or-icon-height: calc(var(--internal-or-header-item-size) - 12px);
                --or-icon-width: calc(var(--internal-or-header-item-size) - 12px);
                z-index: 9999999;
            }

            .shadow {
                -webkit-box-shadow: ${unsafeCSS(DefaultBoxShadowBottom)};
                -moz-box-shadow: ${unsafeCSS(DefaultBoxShadowBottom)};
                box-shadow: ${unsafeCSS(DefaultBoxShadowBottom)};
            }

            #drawer {
                width: 100%;
                position: absolute;
                top: var(--internal-or-header-height);
                max-height: 0;
                height: calc(100% - var(--internal-or-header-height));
                transition: max-height 0.25s ease-out;
                background: var(--internal-or-header-drawer-color);
                color: var(--internal-or-header-drawer-text-color);
                --or-icon-fill: var(--internal-or-header-drawer-text-color);
                --or-icon-height: calc(var(--internal-or-header-drawer-item-size) - 10px);
                --or-icon-width: calc(var(--internal-or-header-drawer-item-size) - 10px);
                overflow: auto;
            }

            #drawer[opened] {
                max-height: 10000px;
                transition: max-height 0.75s ease-in;
            }

            #drawer > div {
                box-sizing: border-box;
                width: 100%;
                height: 100%;
                padding: 10px 0px;
                position: relative;
            }

            .menu-btn {
                background: none;
                border: none;
                cursor: pointer;
                padding: 0 16px;
                height: 100%;
            }

            #menu-btn-mobile {
                display: flex;
                margin-left: auto;
                --or-icon-height: calc(var(--internal-or-header-item-size) - 8px);
                --or-icon-width: calc(var(--internal-or-header-item-size) - 8px);
                --or-icon-fill: var(--internal-or-header-icon-color);
            }

            #menu-btn-mobile #realm-picker > span{
                max-width: 70px;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
            }

            #menu-btn-desktop {
                display: none;
            }

            #desktop-right {
                margin-left: auto;
                padding-right: 10px;
                display: none;
            }

            #desktop-right .menu-item  {
                line-height: var(--internal-or-header-height);
            }

            #desktop-right or-icon {
                color: var(--internal-or-header-icon-color);
            }

            .mobile-bottom-border {
                border-top: 1px solid var(--internal-or-header-drawer-separator-color);
                margin-top: 16px;
                padding-top: 8px;
            }

            .menu-item {
                opacity: 0.7;
                cursor: pointer;
                text-decoration: none !important;
                color: inherit;
                padding: 0 20px;
                font-size: 14px;
            }

            .menu-item:hover,
            .menu-item[selected] {
                opacity: 1;
            }
            #desktop-left .menu-item or-icon {
                margin-right: 10px;
                color: var(--internal-or-header-icon-color);
            }
            #desktop-left .menu-item  {
                display: none;
                line-height: calc(var(--internal-or-header-height) - 4px);
                color: var(--internal-or-header-icon-color);
            }

            #desktop-left .menu-item[selected] {
                display: inline-block;
                line-height: var(--internal-or-header-height);
            }

            #drawer .menu-item  {
                display: block;
                line-height: var(--internal-or-header-drawer-item-size);
                margin: 6px 0;
                padding: 8px 16px;
            }

            #drawer .menu-item  or-icon {
                margin: 0 10px;
            }

            or-mwc-menu {
                margin-right: 10px;
                display: block;
            }

            .or-language-container {
                display: flex;
                height: 50px;
                align-items: center;
            }

            #realm-picker {
                position: relative;
                display: flex;
                height: 50px;
                align-items: center;
                cursor: pointer;
                margin-left: 10px;
            }

            #realm-picker > span {
                margin-right: 2px;
            }

            /* Wide layout: when the viewport width is bigger than 768px, layout
            changes to a wide layout. */
            @media (min-width: 768px) {
                #menu-btn-desktop {
                    display: block;
                }

                #menu-btn-mobile {
                    display: none;
                }

                #drawer {
                    display: none;
                }

                #desktop-right {
                    display: flex;
                }

                #desktop-left .menu-item {
                    display: inline-block;
                }

                #desktop-left .menu-item or-icon {
                    display: none;
                }

                #desktop-left .menu-item[selected] {
                    border-bottom: 4px solid var(--internal-or-header-selected-color);
                    line-height: calc(var(--internal-or-header-height) - 4px);
                }

                #logo {
                    margin: var(--internal-or-header-logo-margin);
                    height: var(--internal-or-header-logo-height);
                    display: block;
                }

                #logo-mobile {
                    display: none;
                }

                #desktop-left ::slotted(*) {
                    display: inline-block;
                }

                #desktop-left ::slotted(*[selected]) {
                    border-bottom: 4px solid var(--internal-or-header-selected-color);
                    line-height: calc(var(--internal-or-header-height) - 4px);
                }
            }

            @media (min-width: 1024px) {


                #desktop-left .menu-item or-icon{
                    display: inline-block;
                }
            }
    `;
    }
}
