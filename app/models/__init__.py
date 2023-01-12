from pymongo import MongoClient

class suDatabase:
    """
    suDatabase is responsible for wrapping the database and models
    """
    def __init__(
        self,
        MONGO_SRV: bool,
        MONGO_HOST: str,
        MONGO_PORT: str,
        MONGO_USER: str,
        MONGO_PASS: str,
    ) -> None:
        self.dbClient = MongoClient(
            f"mongodb{'+srv' if MONGO_SRV else ''}://{MONGO_USER}:{MONGO_PASS}@{MONGO_HOST}{(':'+MONGO_PORT) if not MONGO_SRV else ''}",
            serverSelectionTimeoutMS=5000,
        )
        self.dbScriptUtils = self.dbClient["ScriptUtils"]
