#   ____            _       _   _   _ _   _ _
#  / ___|  ___ _ __(_)_ __ | |_| | | | |_(_) |___
#  \___ \ / __| '__| | '_ \| __| | | | __| | / __|
#   ___) | (__| |  | | |_) | |_| |_| | |_| | \__ \
#  |____/ \___|_|  |_| .__/ \__|\___/ \__|_|_|___/
#                    |_|
#
# Author(s): HyScript7
# License: MIT License
# See LICENSE.txt for more information
#
import discord
from discord.ext import commands


class Core(commands.Cog):
    def __init__(self, bot):
        self.bot = bot
        self.description = "Core commands provide meta information about the bot."

    @commands.hybrid_command(
        name="help", aliases=["cmds", "?"], description="Shows the list of all commands"
    )
    async def _help(self, ctx: commands.Context, category: str = ""):
        if category == "" or not category in self.bot.cogs:
            e = discord.Embed(
                color=int("00FF00", 16), title=f"ScriptUtils - Categories"
            )
            for i in self.bot.cogs:
                c = self.bot.cogs[i]
                e.add_field(
                    name=str(i),
                    value=str(c.description if c.description else str(c)),
                    inline=True,
                )
            e.description = (
                "To view commands for a specific category, use `/help CATEGORY`"
            )
            await ctx.send(embed=e)
            return
        e = discord.Embed(
            color=int("00FF00", 16), title=f"ScriptUtils - Commands for {category}"
        )
        for c in self.bot.cogs[category].get_commands():
            e.add_field(
                name=str(c.name),
                value=str(c.description if c.description else str(c)),
                inline=True,
            )
        await ctx.send(embed=e)

    @commands.hybrid_command(
        name="about", description="Shows information about the bot"
    )
    async def _about(self, ctx: commands.Context):
        e = discord.Embed(color=int("00FF00", 16), title="ScriptUtils - About")
        embedData = {
            "About": [
                "ScriptUtils is a multi-purpose utility bot for discord written in Python",
                "The current modules are Moderation, AutoMod, AntiRaid, Developer, Version Control, Console, Logs, Utils, Tickets, Economy, Backups and Database",
            ],
            "suEconomy": [
                "The ScriptUtils Economy module is an optional addon that adds not only virtual currency, but also stores, items, games and stocks simulation.",
                "Additionally the server admins can toggle whether the economy is server-wide or cross-server.",
            ],
            "FAQ & Support": [
                "If you have any questions or need help, join our discord server where you can find frequently asked questions (FAQ) and support channels.",
                "If you found an issue, bug or just want to support the development of the bot, view the GitHub repository.",
            ],
        }
        for i in embedData:
            e.add_field(name=i, value="\n".join(embedData[i]), inline=False)
        await ctx.send(embed=e)

    @commands.hybrid_command(
        name="credits",
        description="Shows a list of people who contributed to the development of the bot",
    )
    async def _credits(self, ctx: commands.Context):
        e = discord.Embed(color=int("00FF00", 16), title="ScriptUtils - Credits")
        embedData = {
            "About": [
                "Special thanks to all those who contributed to the development of ScriptUtils!",
                "You can view the source code and contribute your self on the official github repository, as well as view the full list of contributors.",
            ],
            "Credits": ["HyScript7", "HyBomtarax"], #? You want to add your self here before opening a pull request.
        }
        for i in embedData:
            e.add_field(name=i, value="\n".join(embedData[i]), inline=False)
        await ctx.send(embed=e)

    @commands.hybrid_command(
        name="version", description="Shows the current version of the bot"
    )
    async def _version(self, ctx: commands.Context):
        e = discord.Embed(color=int("00FF00", 16), title="ScriptUtils - Version")
        e.add_field(name="Version", value="ScriptUtils Version 1.3", inline=False)
        await ctx.send(embed=e)


async def setup(bot):
    # Remove the default help command
    bot.tree.remove_command("help")
    bot.remove_command("help")
    # Register our cog
    await bot.add_cog(Core(bot))
