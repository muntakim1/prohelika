# Prohelika

Prohelika is a tour guide app. It has firebase email based login system and password reset features.

## Installation

```bash
git clone https://github.com/muntakim1/prohelika.git
```

## Usage

goto firebase and login in your firebase account then create a below example.

![alt text](https://bn1302files.storage.live.com/y4mNtxVTHwVp3k2d4C3guM0Nb4s97QQ4HVQB2f2wjVILdVKs5LuPGVUg--l71FDD6ObMzrHELYduloxqkEfS8qZVbpU8EVZjw-GnYaCSNy5_WEWrCGOkJZg5SWdf-LGIfXBtmVSArtnS6dUySx3Z6rV-ZeFhADJuBtf2na-Fwm7DuZItNlkiLRIl8LDiPXZB3dZH_s5KUTFGmk-EpeFXbaulA/image.png?psid=1&width=1240&height=738)

set database rule :

{
  "rules": {
    ".read": true,
    ".write": "auth != null"
  }
}

and goto authentication and enable email auth.



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
## Note
  please make sure you have correct spelling otherwise app will not work.
## License
[MIT](https://choosealicense.com/licenses/mit/)
