using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using Microsoft.EntityFrameworkCore;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PlayerController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public PlayerController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetAllPlayers()
        {
            var players = _context.Players.Select(p => new Player
            {
                Id = p.Id,
                Name = p.Name,
                Team = p.Team,
                Age = p.Age,
                Position = p.Position
            }).ToList();

            return Ok(players);
        }

        [HttpGet("{id}")]
        public IActionResult GetPlayerById(int id)
        {
            var player = _context.Players.Select(p => new Player
            {
                Id = p.Id,
                Name = p.Name,
                Team = p.Team,
                Age = p.Age,
                Position = p.Position
            }).FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }

            return Ok(player);
        }

        [HttpPost]
        public IActionResult CreatePlayer(Player player)
        {
            _context.Players.Add(player);
            _context.SaveChanges();
            return CreatedAtAction(nameof(GetPlayerById), new { id = player.Id }, player);
        }

        [HttpPut("{id}")]
        public IActionResult UpdatePlayer(int id, Player updatedPlayer)
        {
            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }
            player.Name = updatedPlayer.Name;
            player.Position = updatedPlayer.Position;
            player.Age = updatedPlayer.Age;
            player.GoalsScored = updatedPlayer.GoalsScored;
            _context.SaveChanges();
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeletePlayer(int id)
        {
            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }
            _context.Players.Remove(player);
            _context.SaveChanges();
            return NoContent();
        }
    }
}
